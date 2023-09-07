import { Component, OnInit } from '@angular/core';
import { Restaurant } from '../restaurant';
import { RestaurantService } from '../restaurant.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-restaurant-list',
  templateUrl: './restaurant-list.component.html',
  styleUrls: ['./restaurant-list.component.css']
})
export class RestaurantListComponent implements OnInit {

  restaurants: Restaurant[] = [];
  firstName : String = "";
  pageNo: number = 1;
  count: number = 5;


  constructor(private restaurantService: RestaurantService,
    private router: Router) { }

  ngOnInit(): void {
    this.getRestaurant();
  }

  private getRestaurant(){
    this.restaurantService.getAllRestaurants().subscribe(data => {
      this.restaurants = data;

   });
  }

  searchByRestaurantName() : any{
    this.restaurantService.findByName(this.firstName).subscribe(details => {
      this.restaurants= details;
      console.log(details);
    },
      error => {
        console.log(error);
      });
  }

  removeAllRestaurant() : void{
    var status = confirm("Are you sure to delete all the records?");
    if (status == true) {
      this.restaurantService.deleteAllRestaurants().subscribe((details: any) => {
        console.log(details);
        this.getRestaurant();
      },
        (      error: any) => {
          console.log(error);
        })
    }
    else{
    this.getRestaurant();
  }
  }

  getVegRestaurant() {
    this.restaurantService.findByVegDomain().subscribe({
      next: (restaurant: Restaurant[]) => {
      console.log(restaurant);
      this.restaurants = restaurant;
    },
    error: (e) => console.error(e)
  });
  }

  getNonVegRestaurant(){
    this.restaurantService.findByNonVegDomain().subscribe({
      next: (restaurant: Restaurant[]) => {
      console.log(restaurant);
      this.restaurants = restaurant;
    },
    error: (e) => console.error(e)
  });
  }

  restaurantDetails(restaurantId: number){
    this.router.navigate(['restaurant-details', restaurantId]);
  }

  updateRestaurant(restaurantId: number){
    this.router.navigate(['update-restaurant', restaurantId]);
  }

  deleteRestaurant(restaurantId: number){
    this.restaurantService.deleteRestaurantById(restaurantId).subscribe( data => {
      console.log(data);
      this.getRestaurant();
    })
  }

  sortBy(sort: string): void {
    switch (sort) {
      case 'costHigh':
        this.restaurantService.sortByCost("desc","avgCost").subscribe(details => {
    
          console.log('Response from service:',details);
          this.restaurants= details;
      },
          error => {
            console.log(error);
          });
        break;
      case 'costLow':
        this.restaurantService.sortByCost("asc","avgCost").subscribe(details => {
    
          console.log('Response from service:',details);
          this.restaurants= details;
    
        },
          error => {
            console.log(error);
          });
        break;

      case 'ratingLow':
        this.restaurantService.sortByRatings("asc","ratings").subscribe(details => {
    
          console.log('Response from service:',details);
          this.restaurants= details;
    
        },
          error => {
            console.log(error);
          });
          break;
        break;

      case 'ratingHigh':
       this.restaurantService.sortByRatings("desc","ratings").subscribe(details => {
    
        console.log('Response from service:',details);
        this.restaurants= details;
  
      },
        error => {
          console.log(error);
        });
        break;
      default:
        break;
    }
  }

  getRestaurantList(restaurantId: number){
    this.router.navigate(['restaurant-details', restaurantId]);
  }

}
