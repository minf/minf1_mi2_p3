module WorkflowHelper
  # calculate current step count

  def step_count_current
    return workflow.index(@category.workflow_id) + 1 rescue 1
  end

  # number of steps

  def step_count
    return workflow.size
  end
end

