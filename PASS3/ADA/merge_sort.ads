--This is the header file for a generic 
-- bubble sort method

-- Element_type stands for some unknown type
-- for which we must provide a comparison function
-- "compare".  Additionally there is a generic array
-- whose components are of type element_type, but 
-- has an integer range of indexes.

generic
   type Element_Type is private;
   with function Compare(A:  in Element_Type; B: in Element_Type) return Integer;
   type Element_Array is array (Integer range <> ) of Element_Type;
procedure merge_sort ( AnArray:  in out Element_Array);
