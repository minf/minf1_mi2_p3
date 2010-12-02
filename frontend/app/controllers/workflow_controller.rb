class WorkflowController < ApplicationController
  helper_method :chosen_options, :sum

  # reset already chosen options

  def reset
    session[:chosen_options] = []

    redirect_to workflow_url
  end

  def index
    available = RuleEngine.calculate chosen_options

    # fetch all categories for first step

    @category = Category.find_by_workflow_id WORKFLOW[current_step]

    # redirect to finish if no more options available

    return redirect_to finish_url unless @category

    # options matching all and category

    @options = @category.options.find_all_by_article_id available.collect(&:article_id)
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
    return (WORKFLOW["order"] - chosen_categories.collect(&:workflow_id)).first
  end

  def sum
    return chosen_options.collect(&:price).sum
  end
end
