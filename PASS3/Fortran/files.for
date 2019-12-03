     	!Testing reading input from files
	!
	! To read from /write to a file,  we must use
	! formatted I/O,  We will begin with a simple
	! Example that reads a series of integers from
	! a data file and writes the "sum" and "average"
	! of the numbers to an output file.
	!
	PROGRAM Files
	! local variables
	 INTEGER :: current, count=0, sum=0, ioerr
	 REAL :: average
	 
	 ! To attach to a file, we must "open" the file
         ! The unit # ranges from 0-99, with 0 reserved
         ! 5 representing input from the keyboard and 6
         ! output to the console, so DONT use 0, 5, & 6
         !
         ! "file" is the qualified path name of the file
         !
         ! "status" is one of old (existing), new,
         ! replace (if the file doesn't exist it is created, 
         ! otherwise it is overwritten) or scratch (temporary)
         !
         ! "action" is one of read, write, or readwrite
         !
         ! "iostat" indicates the status of the open, its 
         ! argument is an integer variable that stores
         ! the return code, 0 indicates success
         !
	 open(unit= 10, file="input.txt", status = "old", 
     &   action = "read", iostat = ioerr)
	
         if (ioerr == 0) then
           print *, "open successful"
         else
            print *, "open failure"
         end if
	
	   ! open the output file for writing
	open(unit = 11, file="output.txt", status="new",
     &   action = "write", iostat = ioerr);
	
         if (ioerr == 0) then
           print *, "open write successful"
         else
            print *, "open write failure"
         end if
	
        ! read the input file one value at a time using 
        ! a formatted 
        !
        !read the first value

         read(unit=10, fmt='(I5)', iostat=ioerr) current
		print *, current
         
         !! loop while ioerr == 0 to indicate success
         do while (ioerr == 0) 
            sum = sum + current
            count = count + 1
            read(unit=10, fmt='(I5)', iostat=ioerr) current
		  print *, current
         end do

	   ! write the sum and average to the output file
	   ! NOTE: we are casting sum and count to type real
	   !
          average = real(sum)/real(count)
          write(unit=11, fmt='(A,I7,A,F7.2)', iostat=ioerr)
     &    "The sum is ", sum, ". The average is ", average
          
          if(ioerr == 0) then
             print *, "write success"
          else 
             print *, "write fail"
          end if

	   ! when done using a file MUST be closed
	   ! or its contents will be lost

          close(unit=10)
          close(unit=11)
	end program Files
