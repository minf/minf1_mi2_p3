module WorkflowHelper
  # calculate current step count

  def step_count_current
    return WORKFLOW["order"].index(@category.workflow_id) + 1 rescue 1
  end

  # number of steps

  def step_count
    return WORKFLOW["order"].size
  end
end

