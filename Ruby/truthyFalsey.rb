emptyArray = []
arrayWithValue = [1,2,3]
nilValue = nil

puts 0 ? "0 is truthy!" : "0 is falsey"

puts 26 ? "26 is truthy!" : "26 is falsey!"

puts false ? "false is truthy!" : "false is falsey!"

puts true ? "true is truthy!" : "true is falsey!"

puts emptyArray ? "emptyArray is truthy!" : "emptyArray is falsey!"

puts arrayWithValue ? "arrayWithValue is truthy!" : "arrayWithValue is falsey!"

puts nil ? "nil is truthy!" : "nil is falsey!"

puts nilValue ? "nilValue is truthy" : "nilValue is falsey"

puts "" ? "empty string is truthy" : "empty string is falsey"

puts "abc" ? "string with content is truthy" : "string with content is falsey"
