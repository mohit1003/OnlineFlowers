import { Flower } from './../_model/Flower';
import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
  name:'categoryFilter'
})
export class CategoryFilter implements PipeTransform{
  transform(flowers: Flower[], searchTerm: string) {
    if(!flowers || !searchTerm){
      return flowers;
    }
    else{
      return  flowers.filter(flower =>
          flower.category.toLowerCase().indexOf(searchTerm.toLowerCase())!== -1)
    }
  }

}
