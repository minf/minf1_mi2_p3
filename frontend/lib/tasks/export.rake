
namespace :export do
  task :xml => :environment do
    xml = Builder::XmlMarkup.new :target => STDOUT, :indent => 2
    xml.instruct!

    xml.rules do
      options = Option.all

      options.each_with_index do |option, index|
        (index + 1 .. options.size - 1).each do |i|
          xml.rule do
            xml.option_id option.article_id
            xml.referenced_option_id options[i].article_id
            xml.whitelist 1
            xml.value 0
          end
        end
      end
    end
  end
end

