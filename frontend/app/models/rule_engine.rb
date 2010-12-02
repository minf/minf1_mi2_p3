
class RuleEngine
=begin
  def calculate(ids = [])
    socket = TCPSocket.open("localhost", 60666)

    unless ids.blank?
      socket.write "GET #{ids.join ","}\r\n"
    else
      socket.write "GET\r\n"
    end

    res = socket.read

    if res =~ /^ERR/
      return res.strip.gsub(/^ERR\s+/, "")
    end

    return res.strip.gsub(/^OK\s+/, "").split(/,/)
  end
=end

  def self.calculate(ids = [])
    return Option.all.collect(&:article_id) - ids
  end
end

