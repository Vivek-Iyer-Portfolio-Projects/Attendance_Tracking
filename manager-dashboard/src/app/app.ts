import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AttendanceService } from './services/attendance.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './app.html' // <-- Notice this perfectly matches your HTML file name
})
export class App implements OnInit {
  logs: any[] = [];

  constructor(private attendanceService: AttendanceService) {}

  ngOnInit(): void {
    this.fetchLogs();
  }

fetchLogs(): void {
    this.attendanceService.getAttendanceLogs().subscribe({
      next: (data) => {
        console.log('LIVE DATA FROM SPRING BOOT:', data); // <-- Add this line
        this.logs = data;
      },
      error: (err) => {
        console.error('Error fetching logs', err);
      }
    });
  }
}