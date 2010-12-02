class WorkflowController < ApplicationController
  def index
    all = RuleEngine.calculate

    # fetch all categories for first step

    @category = Category.find_by_workflow_id WORKFLOW[WORKFLOW["order"].first]

    # options matching all and category

    @options = @category.options.find_all_by_article_id all
  end

  def choose  
    session[:chosen_options] ||= []

    session[:chosen_options].select! { |entry| entry[:workflow_id] != params[:workflow_id] }

    session[:chosen_options].push :workflow_id => params[:workflow_id], article_id => params[:article_id]
  end
end
