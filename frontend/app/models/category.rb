
class Category < ActiveRecord::Base
  has_many :options
  belongs_to :parent_category
end
