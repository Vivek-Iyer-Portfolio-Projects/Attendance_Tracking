// apiService.ts
const API_BASE_URL = 'http://localhost:8082/api/attendance';

export interface ClockInPayload {
  employeeId: string;
  latitude: number;
  longitude: number;
  bssid: string | null;
  ssid: string | null;
}

export const submitSecureClockIn = async (payload: ClockInPayload) => {
  try {
    const response = await fetch(`${API_BASE_URL}/verify`, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(payload),
    });

    if (!response.ok) {
      // Extract the error message provided by Spring Boot if validation fails
      const errorData = await response.text();
      throw new Error(errorData || 'Server rejected the clock-in attempt.');
    }

const responseText = await response.text();
try {
  // Try to parse it as JSON
  return JSON.parse(responseText);
} catch (e) {
  // If it fails, just return the plain text string (e.g. "Success")
  return responseText;
}
  } catch (error) {
    console.error('API Network Error:', error);
    throw error;
  }
};