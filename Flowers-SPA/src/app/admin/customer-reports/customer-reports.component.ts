import { CityWiseUsers } from './../../_model/CityWiseUsers';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/_model/User';
import { Chart, registerables } from 'chart.js';
import { FileService } from 'src/app/_service/FileService';

@Component({
  selector: 'app-customer-reports',
  templateUrl: './customer-reports.component.html',
  styleUrls: ['./customer-reports.component.css']
})
export class CustomerReportsComponent implements OnInit {

  users: User[] = [];
  allCustomersreport: boolean = true;
  customerReportRegionWise: boolean = false;

  regionWiseChart;
  cityWiseUsers: CityWiseUsers[] = [];
  labelsForRegionWiseReport: string[] = [];
  dataserForRegionWiseReport: number[] = [];


  constructor(private fileService: FileService) {
    Chart.register(...registerables);
  }

  ngOnInit(): void {
    this.fileService.getAllCustomers().subscribe(customers => {
      this.users = Object.assign([], customers);
    })
    this.prepareCategoryWiseReport();
  }


  allCustomersClicked(): void {
    this.allCustomersreport = true;
    this.customerReportRegionWise = false;
  }

  customerReportsRegionWiseClicked(): void {
    this.allCustomersreport = false;
    this.customerReportRegionWise = true;
  }

  prepareCategoryWiseReport(): void{
    this.fileService.getCustomersCityWise().subscribe(customersCityWise => {
      this.cityWiseUsers = Object.assign([], customersCityWise);
      this.prepareDataForCustomerRegionChart();
    })
  }

  prepareDataForCustomerRegionChart(): void {
    this.cityWiseUsers.filter(customer => {
      this.labelsForRegionWiseReport.push(customer.city);
      this.dataserForRegionWiseReport.push(customer.emailCount);
    });
    this.setRegionWiseSalesChart();
  }

  setRegionWiseSalesChart() {
    this.regionWiseChart = new Chart('regionWiseReports', {
      type: 'polarArea',
      data: {
        labels: this.labelsForRegionWiseReport,
        datasets: [
          {
            label: 'Region',
            data: this.dataserForRegionWiseReport,
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
    for (var i = 0; i < 6; i++) {
      color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
  }


}
