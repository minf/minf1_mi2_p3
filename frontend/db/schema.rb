# This file is auto-generated from the current state of the database. Instead of editing this file, 
# please use the migrations feature of Active Record to incrementally modify your database, and
# then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your database schema. If you need
# to create the application database on another system, you should be using db:schema:load, not running
# all the migrations from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended to check this file into your version control system.

ActiveRecord::Schema.define(:version => 20101202142626) do

  create_table "categories", :force => true do |t|
    t.string   "name"
    t.text     "description"
    t.integer  "parent_category"
    t.datetime "created_at"
    t.datetime "updated_at"
    t.string   "workflow_id"
  end

  add_index "categories", ["parent_category"], :name => "index_categories_on_parent_category"

  create_table "options", :force => true do |t|
    t.string   "name"
    t.integer  "category_id"
    t.datetime "created_at"
    t.datetime "updated_at"
    t.string   "image_url"
    t.text     "description"
    t.string   "article_id"
  end

  add_index "options", ["category_id"], :name => "index_options_on_category_id"

end
