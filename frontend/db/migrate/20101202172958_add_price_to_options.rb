class AddPriceToOptions < ActiveRecord::Migration
  def self.up
    add_column :options, :price, :float
  end

  def self.down
    remove_column :options, :price
  end
end
