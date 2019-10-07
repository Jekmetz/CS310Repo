=begin
        read input from user. check validity. sort. repeat.
=end

def bubbleSort(ia)
    for i in 0..(ia.length-2)
        for j in 0..(ia.length-i-2)
            if (ia[j] > ia[j+1])
                t = ia[j]
                ia[j] = ia[j+1]
                ia[j+1] = t
            end
        end
    end
end

cont = true

while cont
    puts "Enter the positive integers you would like to sort delimited by spaces,"
    puts "or 'quit' to exit"
    
    
    inp = gets.to_s
    # converts inp to lowercase
    inp.downcase!
    
    if inp.match /quit|q/
        cont = false
        break
    end
    
    str_array = inp.split(" ")
    num_array = Array.new(str_array.length)
    
    
    valid_entry = true
    
    puts ""
    
    for i in 0..(num_array.length-1)
            
        if !str_array[i].match(/[1-9][0-9]*/)
            puts "only positive integers are allowed"
            valid_entry = false
            break
        end
        num_array[i] = str_array[i].to_i
    end
    
    if valid_entry
        puts "original integer list: "+num_array.to_s
        bubbleSort(num_array)
        puts "bubble sorted: "+num_array.to_s
    end
    puts ""
end

puts "Thanks for sorting!"