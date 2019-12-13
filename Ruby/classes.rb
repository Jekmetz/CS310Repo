=begin
	Showcase Inheritance and classes!
=end

class Person
	def initialize()		#method that gets called on initialization
		@name = "No Name"	#instance variables start with @
		@age = 0		#instance variables are always private :)
	end

	def initialize(name, age)	#overloading initialization
		@name =  name
		@age = age
	end

	def getName		#getting instance variable
		return @name	#return optional
	end

	def printOut				#no need for parenthesis on methods
		puts "Name: " + @name		
		puts "age: " + @age.to_s
	end
end

class Student < Person			#Person is parent class of Student
	def initialize()	#overriding Person's initialize
		super		#calling Person's initialize
		@gpa = 0.0	#initializing new instance variable
	end

	def initialize name,age	#overriding initialize with two arguments
		super		#implicitly passing all parameters with super

		#super() would pass 0 parameters
		#super name would pass one parameter
		#super age,name would pass two
	end

	def initialize(name,age,gpa)	#overloading initialize with 3 parameters
		super name, age 	#passing name and age to super class
		@gpa = gpa
	end

	def printOut #overriding printOut from Person
		#since this super call is in printOut, it is referring
		#to the superclass's printOut method. That's pretty neat!
		super			
		puts "gpa: " + @gpa.to_s
	end
end

############ ENTRY POINT FOR CODE ##################
p1 = Person.new("Bill Billson", 56)
s1 = Student.new("Clarissa Clarson", 19, 3.98)

puts p1.getName + "'s Print Out (type: " + p1.class.to_s + ")"
p1.printOut
puts ""

puts s1.getName + "'s Print Out (type: " + s1.class.to_s + ")"
s1.printOut
puts ""
