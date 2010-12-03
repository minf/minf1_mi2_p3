class AddAttributesToOptions < ActiveRecord::Migration
  def self.up
    add_column :options, :image_url, :string
    add_column :options, :description, :text
  end

  def self.down
    remove_column :options, :image_url
    remove_column :options, :description
  end
end
