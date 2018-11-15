import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {Order, SparePart, SpareType, SparePartsLegendItem} from './warehouse-management';

@Injectable({
  providedIn: 'root'
})
export class WarehouseManagementService {

  constructor(
    private http: HttpClient
  ) { }

  getAllSpares(): Observable<SparePart[]> {
    return this.http.get<SparePart[]>(`${environment.API_URL}/getSpare`);
  }


  getSpareByID(busID): Observable<SparePart[]> {
    const params = new HttpParams().set('busID', busID);
    return this.http.get<any>(`${environment.API_URL}/getSparesForBus`, {headers: new HttpHeaders(), params: params});
  }

  getSpareTypes(): Observable<SpareType[]> {
    return this.http.get<SpareType[]>(`${environment.API_URL}/getSpareTypes`);
  }

  getOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(`${environment.API_URL}/getOrders`);
  }

  addSparePart(spare) {
    return this.http.post(`${environment.API_URL}/createSpare`, spare);
  }

  addSpareType(spareType) {
    console.log(spareType);
    return this.http.post(`${environment.API_URL}/createSpareType`, spareType);
  }

  editSparePart(spare: SparePart) {
    return this.http.post(`${environment.API_URL}/updateSpare`, spare);
  }

  
  getAllSparePartsLegend(): Observable<SparePartsLegendItem[]> {
    return this.http.get<SparePartsLegendItem[]>(`${environment.API_URL}/getSparesLegend`);

}
getSpareTypeByID(spareTypeID): Observable<SpareType[]> {
  let params = new HttpParams().set('spareTypeID', spareTypeID);
  return this.http.get<SpareType[]>(`${environment.API_URL}/getSpareTypesForBus`, {headers: new HttpHeaders(), params: params});
}
}
