=begin
        read input from user. check validity. sort. repeat.
=end


####################SORTING FUNCTIONS##########################
=begin
func: bubbleSort

desc: sorts an array using bubble sort

params: 
    ia - initial array 
=end
def bubbleSort(ia)
    for i in 0..(ia.length-1)
        for j in 0..(ia.length-i-2)
            if (ia[j] > ia[j+1])
                ia[j],ia[j+1]  =  ia[j+1],ia[j]
            end
        end
    end
end

=begin
func: insertionSort

desc: sorts an array using insertion sort

params:
    ia - initial array
=end
def insertionSort(ia)
    #initialize value and j
    value = 0
    j = 0

    for i in 1..(ia.length-1) #for i in indexes of the array...
        value = ia[i];        #value is the current position
        j = i - 1             #j is the index of one less than the current position

        while (j >= 0 && ia[j] > value) #while j is not 0 and there is still something bigger than value...
            #move value down
            ia[j + 1] = ia[j]
            j -= 1
        end

        ia[j + 1] = value #finally set value where it should be.
    end
end

=begin
func: quickSort

desc: sorts an array using quickSort

params:
    ia - initial array
=end
def quickSort(ia)
    quickSortHelper(ia, 0, ia.length-1) #sort the whole thing
end
=begin
func: quickSortHelper

desc: sorts an array using quick sort with the actual parameters

params:
    ia - initial array
    left  - low value to start partitioning from
    h  - high value to partition to
=end
def quickSortHelper(ia, left, h)
    if (left < h)
        p = partition(ia, left, h) #get partition index of the array
        quickSortHelper(ia, left, p - 1) #sort from low to partition index -1
        quickSortHelper(ia, p + 1, h) #sort from p + 1 to high
    end
end

=begin
func: mergeSort

desc: sorts an array using merge sort

params:
    ia - initial array
=end
def mergeSort(ia)
    mergeSortHelper(ia, 0, ia.length-1)
end

=begin
func: mergeSortHelper

desc: sorts an array using merge sort recursively

params:
    ia - initial array
    left - left index
    right - right index
=end
def mergeSortHelper(ia, left, right)
    if(left < right)
        mid = ((left + right) / 2).to_i

        mergeSortHelper(ia, left, mid)
        mergeSortHelper(ia, mid + 1, right)

        merge(ia, left, mid, right)
    end
end

##################UTILITY FUNCTIONS##################
=begin
func: partition

desc: partitions the array ia from left to h so that the 
      value at ia[h] is in the middle and all of the values
      less than that are to the left of it and all the values
      greater than that are to the right of it.

params:
    ia   - initial array
    left - left index
    h    - high index
=end
def partition(ia, left, h)
    piv = ia[h] #choose the pivot as the furthest value
    i = left - 1 #set i to the index of the lowest value
    temp = 0 #temporary swapping variable

    for j in left..(h-1) #for j goes from low to high index..
        if(ia[j] < piv) #if the current value that we are looking at is les than the pivot...
            i += 1      #add one to the lowest position counter...

            #swap those two values
            ia[i],ia[j]  =  ia[j],ia[i]
	end
    end
    #swap so partition is in the middle
    ia[i+1],ia[h]  =  ia[h],ia[i+1]

    return (i+1)
end

def merge(ia, left, mid, right)
    n1 = mid - left + 1
    n2 = right - mid

    leftArr = Array.new(n1);
    rightArr = Array.new(n2);

    #copy data into leftArr and rightArr
    for i in 0..n1
        leftArr[i] = ia[left + i]
    end
    for i in 0..n2
        rightArr[i] = ia[mid + 1 + i]
    end

    #set initial values for left arr index, right arr index, and actual index
    i = 0
    j = 0
    k = left

    while (i < n1 && j < n2)
        if(leftArr[i] <= rightArr[j])
            ia[k] = leftArr[i]
            i += 1
        else
            ia[k] = rightArr[j]
            j += 1
        end
        k += 1
    end

    #copy remaining values into ia if they exist
    while(i < n1)
        ia[k] = leftArr[i]
        i += 1
        k += 1
    end
    while(j < n2)
        ia[k] = rightArr[j]
        j += 1
        k += 1
    end
end



#############PROGRAM ENTRY POINT###################
#REPL VARIABLES
cont = true

=begin POSSIBLE STATES
:EXIT
:RAND_NUMS
:USER_INPUT
:HELP
:UNDEF
=end
state = :UNDEF

num_array = []
processNumArray = false


while cont
    puts "Enter 'help' for a list of commands or 'quit' to cancel"

    processNumArray = false

    inp = gets.to_s
    # converts inp to lowercase
    inp.downcase!
    
    if inp.match /quit|q/
        state = :EXIT
    elsif inp.match /rand|rn/
        state = :RAND_NUMS
    elsif inp.match /input|usr_in/
        state = :USER_INPUT
    elsif inp.match /help|h/
        state = :HELP
    else
        state = :UNDEF
    end
    

    case state
    when :EXIT
        cont = false

    when :RAND_NUMS
        puts "How many random numbers would you like?"
        numRands = gets.to_i
        for i in 0..numRands
            num_array << (rand(50000) - 25000)
        end
        processNumArray = true

    when :USER_INPUT
        puts "Input the numbers you would like"

        inp = gets.to_s

        str_array = inp.split(" ")
        num_array = Array.new(str_array.length)
        
        
        valid_entry = true
        
        puts ""
        
        for i in 0..(num_array.length-1)
                
            if !str_array[i].match(/^(\+|-)?[1-9][0-9]*$/)
                puts "only integers are allowed"
                valid_entry = false
                break
            end
            num_array[i] = str_array[i].to_i
        end

        if !valid_entry
            puts "The numbers you entered were not valid!"
            processNumArray = false
        else
            processNumArray = true
        end

    when :HELP
        puts "quit  - quit program"
        puts "rand  - do this with n rand numbers"
        puts "input - input your own numbers"
        puts "help  - display this help menu"

    when :UNDEF
        puts "That command was not recognized! Try again!"
    else

    end
    
    if processNumArray
        puts "original integer list: "+num_array.to_s

        #Create copy of the array
        clone_array = []
        num_array.each{|n| clone_array << n.dup}
        starting = Process.clock_gettime(Process::CLOCK_MONOTONIC)
        bubbleSort(clone_array)
        ending = Process.clock_gettime(Process::CLOCK_MONOTONIC)
        #puts "sorted: "+clone_array.to_s
        puts "BubbleSort took " + ((ending - starting) * 1000).to_s + "ms"

        clone_array = []
        num_array.each{|n| clone_array << n.dup}
        starting = Process.clock_gettime(Process::CLOCK_MONOTONIC)
        insertionSort(clone_array)
        ending = Process.clock_gettime(Process::CLOCK_MONOTONIC)
        #puts "sorted: "+clone_array.to_s
        puts "Insertion sort took " + ((ending - starting) * 1000).to_s + "ms"

        clone_array = []
        num_array.each{|n| clone_array << n.dup}
        starting = Process.clock_gettime(Process::CLOCK_MONOTONIC)
        quickSort(clone_array)
        ending = Process.clock_gettime(Process::CLOCK_MONOTONIC)
        #puts "sorted: "+clone_array.to_s
        puts "Quick sort took " + ((ending - starting) * 1000).to_s + "ms"

        clone_array = []
        num_array.each{|n| clone_array << n.dup}
        starting = Process.clock_gettime(Process::CLOCK_MONOTONIC)
        mergeSort(clone_array)
        ending = Process.clock_gettime(Process::CLOCK_MONOTONIC)
        #puts "sorted: "+clone_array.to_s
        puts "Merge Sort took " + ((ending - starting) * 1000).to_s + "ms"

        puts "Press Enter to continue"
        gets

        puts "sorted: "+clone_array.to_s
    end
    puts ""
end

puts "Thanks for sorting!"
