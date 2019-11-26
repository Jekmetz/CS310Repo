--right. So Merge sort works by splitting the array in half
--all the way down to single item arays and then combining
--the arrays using a double pointer technique. The combine
--arrays procedure simply takes two sorted arrays and combines
--them into 1 sorted array. This is done by looking at the left
--most item in each arrays and taking the least one. Whichever
--array it took from gets its pointer incremented. Rinse and repeat.

procedure merge_sort ( AnArray:  in out Element_Array) is

	procedure merge_sort_helper ( AnArray: in out Element_Array; left: in Integer; right: in Integer ) is
	
		procedure combine_arrays(AnArray: in out Element_Array; left: in Integer; mid: in Integer; right: in Integer) is

			length1: Integer := mid - left + 1;  --length of the first sub arr
			length2: Integer := right - mid; --length of the second sub arr
			
			--temporary arrays for storing the sorted halves.
			leftArr: Element_Array (1 .. length1);
			rightArr: Element_Array(1 .. length2);

			lp : Integer := 1;    --pointer to the index in the left array
			rp : Integer := 1;    --pointer to the index in the right array
			ap : Integer := left; --pointer to the overwriting index in AnArray
	
		begin
			--copy data into leftArr and rightArr (left -> mid and then mid+1 - right)
			for I in leftArr'Range loop
				leftArr(I) := AnArray(left + I - 1);
			end loop;
			for J in rightArr'Range loop
				rightArr(J) := AnArray(mid + J);
			end loop;

			--start from the left of the sorted arrays and overwrite
			--the values in AnArray from left to right until we run
			--out of values from one array
			while lp <= length1 and then rp <= length2 loop
				if (Compare(leftArr(lp), rightArr(rp)) <= 0) then
					AnArray(ap) := leftArr(lp);
					lp := lp + 1;
				else
					AnArray(ap) := rightArr(rp);
					rp := rp + 1;
				end if;
				ap := ap + 1;
			end loop;

			--now that we have gone through at least one of the sorted
			--arrays, let us add the rest from the remaining array!
			--(should only be left or right or none)
			while lp <= length1 loop
				AnArray(ap) := leftArr(lp);
				lp := lp + 1;
				ap := ap + 1;
			end loop;

			while rp <= length2 loop
				AnArray(ap) := rightArr(rp);
				rp := rp + 1;
				ap := ap + 1;
			end loop;

			--donezo!

		end combine_arrays;

		mid: Integer := (left + right) / 2;	-- middle index of the array
	begin
		if(left < right) then
			--Sort both halves and then merge the two sorted arrays
			merge_sort_helper(AnArray, left, mid);
			merge_sort_helper(AnArray, mid + 1, right);
			combine_arrays(AnArray, left, mid, right);
		end if;

	end merge_sort_helper;
begin
	merge_sort_helper(AnArray, AnArray'First, AnArray'Last);
end merge_sort;
