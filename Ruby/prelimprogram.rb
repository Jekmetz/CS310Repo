arr = Array.new(10) { rand(1..20) }

def printarr (arr)
	$STDOUT.print("[")
	for i in 0..(arr.length - 1)
		$STDOUT.print("#{arr[i]}, ")
	end	
	$STDOUT.print("#{arr[arr.length - 1]}]\n")
end

printarr(arr)
