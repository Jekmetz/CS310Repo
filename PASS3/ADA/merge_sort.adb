-- our generic bubble sort procedure has a single
-- parameter, and array of our generic array type
-- elementArray.  NOTICE the in out!!!!
-- This means the array will be filled by the client
-- program and might be changed by our bubble sort subprogram
procedure merge_sort ( AnArray:  in out Element_Array) is

Temp: Element_Type; -- temp place holder
begin

-- Since we don't know the absolute bounds of the
-- array we use the attributes 'First and 'Last to 
-- iterate from the first to the last element no
-- matter what the actual indexes are.

	--TODO: REPLACE WITH MERGE SORT LOGIC --	
--   for I in anArray'First .. anArray'Last loop
--      for J in anArray'First .. anArray'Last-1 loop

-- call the compare method to compare adjacent elements
--         if (Compare(AnArray(J), Anarray(J+1)) > 0) then
--            Temp := Anarray(J);
--            Anarray(J) := AnArray(J+1);
--            AnArray(J+1):= Temp;
--         end if;

--      end loop;
--   end loop;

end merge_sort;
