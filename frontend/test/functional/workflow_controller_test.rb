require 'test_helper'

class WorkflowControllerTest < ActionController::TestCase
  def test_reset
    @request.session[:chosen_options] = [ 1 ]

    post :reset

    assert_response :redirect

    assert_equal [], @response.session[:chosen_options]
  end

  def test_start
    get :index

    assert_response :success
  end

  def test_index
    @request.session[:chosen_options] = [ options(:yaris).id, options(:three_doors).id ]

    get :index

    assert_response :success
  end

  def test_finish
    get :finish

    assert_response :success
  end

  def test_choose_option
    post :choose_option, :id => options(:yaris)

    assert_response :redirect

    assert_equal [ options(:yaris).id ], @response.session[:chosen_options]
  end

  def test_delete_option
    @request.session[:chosen_options] = [ options(:yaris).id, options(:three_doors).id ]

    post :delete_option, :id => options(:yaris)

    assert_response :redirect

    assert_equal [ options(:three_doors).id ], @response.session[:chosen_options]
  end
end
