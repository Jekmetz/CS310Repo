with Text_Io;
use Text_Io;
with merge_sort;

-- sort an integer array with our
-- generic bubble sort method
procedure Test_sort is

-- define a compare function for integers
   function Compare(A: in Integer; B: in Integer) return Integer is
   begin
         if ( A = B) then
		return (0);
	    elsif (A >= B) then 
		return (1);
         else 
		return (-1);
         end if;
   end Compare;

-- define an array type of integers
   type Int_Array is array (Integer range <>) of Integer;

-- create an interger array of 10 elements
   Myarray: Int_Array(1..10);

-- INSTANTIATE our bubble Sort subprogram
   procedure Sort_Ints is new merge_sort(Element_Type=> Integer,
                                          Compare => Compare,
                                          Element_Array => Int_Array);

   package Int_Io is new Text_Io.Integer_Io(Num=>Integer);
   use Int_Io;
begin

-- fill the array
   for I in myArray'First.. myArray'Last loop
      Put_Line("please enter an integer value ");
      Get(Myarray(I));
   end loop;

-- print the unsorted array
      Put_Line("the unsorted array is: ");
   for I in myArray'First.. myArray'Last loop
      Put (MyArray(I));
   end loop;
   New_Line;
  
  -- sort the array
   Sort_Ints(Myarray);

   -- print the sorted array
   Put_Line("the sorted array is: ");
   for I in myArray'First.. myArray'Last loop
      Put (MyArray(I));
   end loop;
   New_Line;

end Test_Sort;
