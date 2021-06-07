import { DatePipe } from '@angular/common';
import { Flower } from './../../_model/Flower';
import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/_service/FileService';
import { Chart, registerables } from 'chart.js';
import * as alertify from 'alertifyjs';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css'],
})
export class ReportsComponent implements OnInit {
  mostSoldFlower: Flower;
  leastSoldFlower: Flower;
  dailyReportChartValue: Flower[];
  weeklyReportChartValue: Flower[];
  monthlyReportChartValue: Flower[];
  categoryWiseChartValue: Flower[];

  dailyReportChart;
  weeklyReport;
  monthlyReport;
  categoryWiseChart;

  labelsForDailyReport: number[] = [];
  dataserForDailyReport: number[] = [];

  labelsForWeeklyReport: number[] = [];
  dataserForweeklyReport: number[] = [];

  labelsForMonthlyReport: number[] = [];
  dataserForMonthlyReport: number[] = [];

  labelsForCategoryWiseReport: number[] = [];
  dataserForCategoryWiseReport: number[] = [];

  todaysDate: string;

  constructor(private fileService: FileService, private datePipe: DatePipe) {
    Chart.register(...registerables);
    this.todaysDate = datePipe.transform(new Date(), 'yyyy-MM-dd');
  }

  ngOnInit(): void {
    this.getMostSoldProduct();
    this.getLeastSoldProduct();
    this.prepareDataForDailyChart();
    this.prepareWeeklyChart();
    this.prepareMonthlyReport();
    this.prepareCategoryWiseReport();
  }

  getMostSoldProduct() {
    this.fileService.generateReportMostSoldFlower().subscribe(
      (mostSoldFlower) => {
        this.mostSoldFlower = Object.assign(mostSoldFlower);
      },
      (err) => {
        alertify.error('Error generating most sold report');
      }
    );
  }

  getLeastSoldProduct() {
    this.fileService.generateReportLeastSoldFlower().subscribe(
      (leastSoldFlower) => {
        this.leastSoldFlower = Object.assign(leastSoldFlower);
      },
      (err) => {
        alertify.error('Error generating least sold report');
      }
    );
  }

  prepareDataForDailyChart() {
    this.fileService.generateDailyReport().subscribe(
      (dailyreport) => {
        this.dailyReportChartValue = Object.assign(dailyreport);

        this.dailyReportChartValue.filter((flower) => {
          this.labelsForDailyReport.push(parseInt(flower.id));
        });

        this.dailyReportChartValue.filter((flower) => {
          this.dataserForDailyReport.push(flower.count);
        });
        this.setDailySalesChart();
      },
      (err) => {
        alertify.error('Error generating daily report');
      }
    );
  }

  prepareWeeklyChart() {
    this.fileService.generateLastWeeksReport().subscribe(
      (weeklyReport) => {
        this.weeklyReportChartValue = Object.assign(weeklyReport);

        this.weeklyReportChartValue.filter((flower) => {
          this.labelsForWeeklyReport.push(parseInt(flower.id));
        });

        this.weeklyReportChartValue.filter((flower) => {
          this.dataserForweeklyReport.push(flower.count);
        });
        this.setWeeklySalesChart();
      },
      (err) => {
        alertify.error('Error generating weekly report');
      }
    );
  }

  prepareMonthlyReport() {
    this.fileService.generateMonthlyReport().subscribe(
      (monthlyReport) => {
        this.monthlyReportChartValue = Object.assign(monthlyReport);

        this.monthlyReportChartValue.filter((flower) => {
          this.labelsForMonthlyReport.push(parseInt(flower.id));
        });

        this.monthlyReportChartValue.filter((flower) => {
          this.dataserForMonthlyReport.push(flower.count);
        });
        this.setMonthlySalesChart();
      },
      (err) => {
        alertify.error('Error generating Monthly report');
      }
    );
  }

  prepareCategoryWiseReport() {
    this.fileService.generateCategoryWiseReport().subscribe(
      (categoryWiseChart) => {
        this.categoryWiseChartValue = Object.assign(categoryWiseChart);
        console.log(this.categoryWiseChartValue);
        this.categoryWiseChartValue.filter((flower) => {
          this.labelsForCategoryWiseReport.push(parseInt(flower.id));
        });

        this.categoryWiseChartValue.filter((flower) => {
          this.dataserForCategoryWiseReport.push(flower.count);
        });
        this.setCategoryWiseSalesChart();
      },
      (err) => {
        alertify.error('Error generating Category-wise report');
      }
    );
  }

  setDailySalesChart() {
    this.dailyReportChart = new Chart('dailyReport', {
      type: 'bar',
      data: {
        labels: this.labelsForDailyReport,
        datasets: [
          {
            label: 'Sales Daily' + ' ' + this.todaysDate,
            data: this.dataserForDailyReport,
            backgroundColor: this.getRandomColor(),
            borderWidth: 1,
          },
        ],
      },
      options: {
        responsive: true,
        scales: {
          y: {
            title: {
              display: true,
              text: 'Sales',
            },
            beginAtZero: true,
            ticks: {
              precision: 0,
            },
          },
          x: {
            grid: {
              offset: true
            },
            title: {
              display: true,
              text: 'Flower ID',
            },
            beginAtZero: true,
            ticks: {
              precision: 0,
            },
          },
        },
      },
    });
  }

  setWeeklySalesChart() {
    this.weeklyReport = new Chart('weeklyReport', {
      type: 'bar',
      data: {
        labels: this.labelsForWeeklyReport,
        datasets: [
          {
            label: 'Sales Weekly' + ' ' + this.todaysDate,
            data: this.dataserForweeklyReport,
            backgroundColor: this.getRandomColor(),
            borderWidth: 1,
          },
        ],
      },
      options: {
        responsive: true,
        scales: {
          y: {
            title: {
              display: true,
              text: 'Sales',
            },
            beginAtZero: true,
            ticks: {
              precision: 0,
            },
          },
          x: {
            title: {
              display: true,
              text: 'Flower ID',
            },
          },
        },
      },
    });
  }

  setMonthlySalesChart() {
    this.monthlyReport = new Chart('monthlyReport', {
      type: 'bar',
      data: {
        labels: this.labelsForMonthlyReport,
        datasets: [
          {
            label: 'Sales Monthly' + ' ' + this.todaysDate,
            data: this.dataserForMonthlyReport,
            backgroundColor: this.getRandomColor(),
            borderWidth: 1,
          },
        ],
      },
      options: {
        responsive: true,
        scales: {
          y: {
            title: {
              display: true,
              text: 'Sales',
            },
            beginAtZero: true,
            ticks: {
              precision: 0,
            },
          },
          x: {
            title: {
              display: true,
              text: 'Flower ID',
            },
          },
        },
      },
    });
  }

  setCategoryWiseSalesChart() {
    this.monthlyReport = new Chart('categoryWiseReport', {
      type: 'polarArea',
      data: {
        labels: this.labelsForCategoryWiseReport,
        datasets: [
          {
            label: 'Category' + ' ' + this.todaysDate,
            data: this.dataserForCategoryWiseReport,
            backgroundColor: this.getRandomColor(),
            borderWidth: 1,
          },
        ],
      },
      options: {
        responsive: true,
        scales: {
          y: {
            beginAtZero: true,
            ticks: {
              precision: 0,
            },
          },
          x: {
          },
        },
      },
    });
  }

   getRandomColor() {
    var letters = '0123456789ABCDEF'.split('');
    var color = '#';
    for (var i = 0; i < 6; i++ ) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}
}
