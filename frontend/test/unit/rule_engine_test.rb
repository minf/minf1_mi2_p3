require 'test_helper'

class RuleEngineTest < ActiveSupport::TestCase
  def test_calculate_all
    # can't run assert_equal, because we don't know what the rule engine writes back to us

    assert RuleEngine.calculate
  end

  # if we ask for compatible options for a specific option, the result set may not include the option

  def test_calculate_without
    assert ! RuleEngine.calculate(Array Option.first).include?(Option.first)
  end
end
