
class Category < ActiveRecord::Base
  has_many :options
  has_one :parent_category
end
