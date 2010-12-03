require 'test_helper'

class RuleEngineTest < ActiveSupport::TestCase
  def test_calculate_all
    assert_equal Option.all, RuleEngine.calculate
  end
end
