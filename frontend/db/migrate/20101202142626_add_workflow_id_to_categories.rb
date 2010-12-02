class AddWorkflowIdToCategories < ActiveRecord::Migration
  def self.up
    add_column :categories, :workflow_id, :string
  end

  def self.down
    remove_column :categories, :workflow_id
  end
end
