class CreateOptions < ActiveRecord::Migration
  def self.up
    create_table :options do |t|
      t.string :name
      t.integer :category_id

      t.timestamps
    end

    add_index :options, :category_id
  end

  def self.down
    drop_table :options
  end
end
