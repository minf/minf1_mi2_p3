
class RuleEngine
  # query the rule engine for list of ids

  def calculate(options = [])
    socket = TCPSocket.open APP_CONFIG["rule_engine_host"], APP_CONFIG["rule_engine_port"]

    if options.blank?
      socket.write "GET\r\n"
    else
      socket.write "GET #{options.collect(&:id).join ","}\r\n"
    end

    res = socket.readline

    raise res.strip.gsub(/^ERR\s+/, "") if res=~ /^ERR/

    return Option.find_all_by_id res.strip.gsub(/^OK\s+/, "").split(/,/).collect(&:strip).reject(&:nil?)
  end

  def self.calculate(options = [])
    return Option.all - options
  end
end

