// Call the dataTables jQuery plugin
$(document).ready(function() {
  $('#dataTable').DataTable({
    "aaSorting": [],
    columnDefs: [{
    orderable: false,
    targets: 3
    }]
  });
});
