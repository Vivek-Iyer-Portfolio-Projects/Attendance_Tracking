import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AttendanceService {
  // Pointing directly to your Spring Boot API
  private apiUrl = 'http://localhost:8082/api/attendance';

  constructor(private http: HttpClient) { }

  // We will build this matching GET endpoint in Java next
  getAttendanceLogs(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/logs`);
  }
}