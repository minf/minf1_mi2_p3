
# the workflow implements a wizard to choose options
# the order is determined by config/workflow.yml

class WorkflowController < ApplicationController
  helper_method :chosen_options, :sum, :workflow

  # reset already chosen options

  def reset
    session[:chosen_options] = []
    session[:skipped_steps] = []

    redirect_to workflow_url
  end

  def index
    # redirect to finish if no more steps

    return redirect_to finish_url unless current_step

    available = RuleEngine.calculate chosen_options

    # fetch all categories for current step

    @category = Category.find_by_workflow_id current_step

    # skip step if category not found

    return skip_step unless @category

    # options matching available and category

    @options = @category.options.find_all_by_article_id available.collect(&:article_id), :order => :price

    # skip step and try again if no options available

    return skip_step if @options.blank?
  end

  def choose_option
    available = RuleEngine.calculate chosen_options

    option = Option.find params[:id]

    return redirect_to workflow_url unless available.include? option

    # save chosen options in session

    session[:chosen_options] ||= []

    # remove already chosed option with same workflow id

    session[:chosen_options].push option.id
    session[:chosen_options].uniq!

    redirect_to workflow_url
  end

  # remove already chosen option

  def delete_option
    option = Option.find params[:id]

    session[:chosen_options].delete option.id
    session[:skipped_steps] = []

    redirect_to workflow_url
  end

  private

  # map options in session to objects

  def chosen_options
    Option.find_all_by_id session[:chosen_options]
  end

  # map options in session to categories

  def chosen_categories
    return chosen_options.collect &:category
  end

  # calculate current stop from already chosen options and workflow

  def current_step
    return (workflow - chosen_categories.collect(&:workflow_id) - session[:skipped_steps].to_a).first
  end

  # calculate current sum for chosen options

  def sum
    return chosen_options.collect(&:price).sum
  end

  # return array of steps from workflow

  def workflow
    return WORKFLOW.collect(&:values).flatten
  end

  # skip current step and try again

  def skip_step
    session[:skipped_steps] ||= []
    session[:skipped_steps].push current_step

    return redirect_to workflow_url
  end
end
