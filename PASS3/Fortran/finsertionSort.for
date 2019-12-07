	program finsertionSort
	implicit none 
	 character(len=100) :: file_name !variable for file name
	 integer :: ioerr, i, j !io errors, and couting variables
	 integer, dimension(1:100) :: num_arr !initial number array
		
		!Grab the file name from the user!
		print *, "Enter the file you wish to grab numbers from!:"
		read(*, '(A)') file_name 
	
		open(unit=10, file=file_name, status="old", action="read",
     &       iostat=ioerr)
		
		if(ioerr == 0) then !If there was no file open error...
			print *, "Open succesful!"
			read(10, *) num_arr(1:100) !read 100 values from the file
			
			!close the file after we're done with it!
			close(unit=10)
			
			call sortArr(num_arr) !sort that pajort
			
			do i = 1, 10  !i goes from 1 to 10
				do j = 1,9 !j goes from 1 to 9 (10 to a line, 1 newline)
					!write on the same line the (i-1)*10 + jth thing
					write(*, fmt='(I7)', advance='no') num_arr((i-1)*10+j)
				end do
				!write the number and a new line
				write(*, fmt='(I7)') num_arr(i * 10)
			end do
			
		else !if there was a file open error...
			print *, "Yeah that file didn't work! Try again next time!"
		end if
		

	end program finsertionSort
	
	subroutine sortArr(arr)
		implicit none
		integer, dimension(1:100), intent(inout) :: arr !array
		integer :: i,j,v !i and j are counters... v is the current value
		
		!your standard every day insertion sort :)
		do i = 2, 100, 1 !i goes from second element to last element
			v = arr(i) !set current value
			j = i - 1  !j points at value before current value
			
			!while j has not reached the beginning and arr(j) is still
			!bigger than the current value 
			do while (j >= 1 .and. arr(j) > v)
				arr(j+1) = arr(j) !swap that value
				j = j-1
			end do
			arr(j+1) = v !put v over the dupe value
		end do
	end subroutine sortArr
