let numberOfItems = $('#page .table-row').length;
let limitPerPage = 5;
$('#page .table-row:gt(' + (limitPerPage - 1) + ')').hide();
let totalPages = Math.ceil(numberOfItems / limitPerPage);
$(".pagination").append("<li class='current-page page-item active'>" +
    "<a class='page-link' href='javascript:void(0)'>" + 1 + "</a></li>");

/**
 * Loop to insert page number for each sets of items equal to page limit
 * (e.g., limit of 4 with 20 total items = insert 5 pages)
 */
for (let i = 2; i <= totalPages; i++) {
    $(".pagination").append("<li class='current-page page-item'>" +
        "<a class='page-link' href='javascript:void(0)'>" + i + "</a></li>");
}

$(".pagination").append("<li class='page-item' id='next-page'>" +
    "<a class='page-link' href='javascript:void(0)' aria-label=Next><span aria-hidden=true>&raquo;</span></a></li>");

/**
 * Function that displays new items based on page number that was clicked
 */
$(".pagination li.current-page").on("click", function () {
    if ($(this).hasClass('active')) {
        return false;
    } else {
        let currentPage = $(this).index();
        $(".pagination li").removeClass('active');
        $(this).addClass('active');
        $("#page .table-row").hide();
        let grandTotal = limitPerPage * currentPage; // Get the total number of items up to the page number that was clicked on

        // Loop through total items, selecting a new set of items based on page number
        for (let i = grandTotal - limitPerPage; i < grandTotal; i++) {
            $("#page .table-row:eq(" + i + ")").show(); // Show items from the new page that was selected
        }
    }

});
/**
 * Function to navigate to the next page when users click on the next-page id (next page button)
 */
$("#next-page").on("click", function () {
    let currentPage = $(".pagination li.active").index();
    if (currentPage === totalPages) {
        return false;
    } else {
        currentPage++;
        changePageContent(currentPage);
    }
});
/**
 * Function to navigate to the previous page when users click on the previous-page id (previous page button)
 */
$("#previous-page").on("click", function () {
    let currentPage = $(".pagination li.active").index();
    if (currentPage === 1) {
        return false;
    } else {
        currentPage--;
        changePageContent(currentPage);
    }
});

/**
 * Function that changes the display of page content
 * @param currentPage page whose content should be displayed
 */
function changePageContent(currentPage) {
    $(".pagination li").removeClass('active');
    $("#page .table-row").hide();
    let grandTotal = limitPerPage * currentPage;
    for (let i = grandTotal - limitPerPage; i < grandTotal; i++) {
        $("#page .table-row:eq(" + i + ")").show();
    }
    $(".pagination li.current-page:eq(" + (currentPage - 1) + ")").addClass('active');
}