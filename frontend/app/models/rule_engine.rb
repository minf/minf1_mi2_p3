
class RuleEngine
  # query the rule engine for list of ids

=begin
  def calculate(ids = [])
    socket = TCPSocket.open APP_CONFIG["rule_engine_host"], APP_CONFIG["rule_engine_port"]

    unless ids.blank?
      socket.write "GET #{ids.join ","}\r\n"
    else
      socket.write "GET\r\n"
    end

    res = socket.read

    raise res.strip.gsub(/^ERR\s+/, "") if res=~ /^ERR/
    return Option.find_all_by_id res.strip.gsub(/^OK\s+/, "").split(/,/)
  end
=end

  def self.calculate(ids = [])
    return Option.all - ids
  end
end

