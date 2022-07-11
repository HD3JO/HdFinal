// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

// Pie Chart Example
var ctx = document.getElementById("myPieChart");
let pnameList = [];
let cnt = [];
let totalCnt = 0;
let idx = $('.pnameCnt').length;
let labels = '';

for(let i = 0; i < idx; i++){
	pnameList.push($('#pname'+i).val());
	cnt.push($('#cntByPsid'+i).val());
	totalCnt += cnt[i];
}

var myPieChart = new Chart(ctx, {
  type: 'doughnut',
  data: {
    labels: pnameList,//[pnameList[0], pnameList[1]],
    datasets: [{
      data: cnt,
      backgroundColor: ['#4e73df', '#1cc88a', '#36b9cc', '#afc5db', '#e9df56', '#00a29b', '#a7b901', '#ffbd60', '#f76e77'],
      hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf', '#36b9cc'],
      hoverBorderColor: "rgba(234, 236, 244, 1)",
    }],
  },
  options: {
    maintainAspectRatio: false,
    tooltips: {
      backgroundColor: "rgb(255,255,255)",
      bodyFontColor: "#858796",
      borderColor: '#dddfeb',
      borderWidth: 1,
      xPadding: 15,
      yPadding: 15,
      displayColors: false,
      caretPadding: 10,
    },
    legend: {
      display: false
    },
    cutoutPercentage: 80,
  },
});
