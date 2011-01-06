
class RuleEngine
  # query the rule engine for list of ids

  def self.calculate(options = [])
    socket = TCPSocket.open APP_CONFIG["rule_engine_host"], APP_CONFIG["rule_engine_port"]

    if options.blank?
      socket.puts "GET"
    else
      socket.puts "GET #{options.collect(&:article_id).join ","}"
    end

    res = socket.readline

    socket.close

    raise res.strip.gsub(/^ERR\s+/, "") if res=~ /^ERR/

    return Option.find_all_by_article_id res.strip.gsub(/^OK\s+/, "").split(/,/).collect(&:strip).reject(&:nil?)
  end

#  def self.calculate(options = [])
#    return Option.all - options
#  end
end

