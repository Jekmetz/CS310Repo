boolt = true
boolf = false
int = 3
complex = 3 + 2i
float = 5.0e3
string = ""
array = []
map = {:a => "1", :b => "2"}
symb = :symbol
nilVal = nil

puts ""
puts boolt.class.to_s   #TrueClass
puts boolf.class.to_s   #FalseClass
puts int.class.to_s     #Integer
puts complex.class.to_s #Complex
puts float.class.to_s   #Float
puts string.class.to_s  #String
puts array.class.to_s   #Array
puts map.class.to_s     #Hash
puts symb.class.to_s    #Symbol
puts nilVal.class.to_s  #NilClass
puts ""
