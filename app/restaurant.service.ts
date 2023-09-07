import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { HttpClient } from '@angular/common/http';
import { Restaurant } from './restaurant';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {
  findByRestaurantName(firstName: String) {
    throw new Error('Method not implemented.');
  }
  
  private baseURL = "http://localhost:8080/api/v1/restaurant";
  CreateRestaurant: any;

  constructor(private httpClient: HttpClient) { }

  getRestaurantList(): Observable<any> {
    return this.httpClient.get<Restaurant[]>(`${this.baseURL}/restaurant`);
  }
 
  findByVegDomain():Observable<any> {
    return this.httpClient.get<Restaurant[]>(`${this.baseURL}/VegRestaurant`);
  }

  findByNonVegDomain():Observable<any> {
    return this.httpClient.get<Restaurant[]>(`${this.baseURL}/nonVegRestaurant`);
  }

  getAllRestaurants(): Observable<Restaurant[]>{
    return this.httpClient.get<Restaurant[]>(`${this.baseURL}`);
  }

  findByName(name: any): Observable<Restaurant[]> {
    return this.httpClient.get<Restaurant[]>(`${this.baseURL}?restaurantName=${name}`);
  }

  createRestaurant(restaurant: Restaurant): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}/createRestaurant`, restaurant);
  }

  getRestaurantById(restaurantId: number): Observable<Restaurant>{
    return this.httpClient.get<Restaurant>(`${this.baseURL}/readRestaurant/${restaurantId}`);
  }

  updateRestaurant(restaurantId: number, restaurant: Restaurant): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/updateRestaurant/${restaurantId}`, restaurant);
  }

  deleteRestaurantById(restaurantId: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/deleteRestaurant/${restaurantId}`);
  }

  deleteAllRestaurants(): Observable<any> {
    return this.httpClient.delete(`${this.baseURL}/deleteAllRestaurants`);
  }

  // findByIsVegRestaurant(): Observable<Restaurant[]> {
  //   return this.httpClient.get<Restaurant[]>(`${this.baseURL}/vegRestaurant`);
  // }

  sortByRatings(pathVar : string,fieldName : string): Observable<Restaurant[]> {
    return this.httpClient.get<Restaurant[]>(`${this.baseURL}/filter/Ratings/${pathVar}?ratings=${fieldName}`);
  }

  sortByCost(pathVar : string,fieldName : string): Observable<Restaurant[]> {
    return this.httpClient.get<Restaurant[]>(`${this.baseURL}/filter/Price/${pathVar}?price=${fieldName}`);
  }
 
  
}
