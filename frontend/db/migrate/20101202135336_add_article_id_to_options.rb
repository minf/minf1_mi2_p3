class AddArticleIdToOptions < ActiveRecord::Migration
  def self.up
    add_column :options, :article_id, :string
  end

  def self.down
    remove_column :options, :article_id
  end
end
