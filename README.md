# Assumptions made so far as on 12/20/2022
1. Start and End Dates are ignored for now
2. Downloads of data in different formats is ignored as I still have to research into the process/logic involved. I do not have experience to do this in Java
3. I took Status into another table assuming that at the data entry page for Customers table is also not hardcoded and the drop down is populated from this Status table.
4. If only plan id is selected, all the customer applications belonging to the selected plan id are listed
5. If only status id is selected, all the customer applications belonging to the selected status id are listed
6. If both are selected, all the customer applications belonging to the selected plan and status are listed
7. If none are selected, then all the customer applications are displayed, so which is why the moment the form loads the entire customer list is loaded.
8. As Dates logic is made clear, I will add that logic also into the customer rest controller.
