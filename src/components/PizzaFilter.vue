<template>
    <div class="pizza-filter">
        <div class="toggle" :class="{ 'selected': isSelected(ingredient.name) }" v-for="ingredient in ingredients" :key="ingredient.id">
            <div @click="toggleIngredient(ingredient.name)" >
                {{ ingredient.name }}
            </div>
        </div>
    </div>
</template>

<script>
export default {
  data() {
    return {
      ingredients: [
        { id: 1, name: 'Tomato' },
        { id: 2, name: 'Cheese' },
        { id: 3, name: 'Olives' },
        { id: 4, name: 'Onion' },
        { id: 5, name: 'Chicken' },
        { id: 6, name: 'Corn' }
    ],
      selectedIngredients: [],
    };
  },
  methods: {
    toggleIngredient(name) {
      const index = this.selectedIngredients.indexOf(name);
      if (index === -1) {
        this.selectedIngredients.push(name);
      } else {
        this.selectedIngredients.splice(index, 1);
      }
      this.$emit('filterPizzas', this.selectedIngredients);
    },
    isSelected(name) {
      return this.selectedIngredients.includes(name);
    },
    } 
};
</script>

<style scoped>
.pizza-filter{
    margin-top: 20px;
    max-width: 70%;
    display: flex;
    flex-wrap: wrap;
    flex-direction: row;
    justify-content: center;
    align-items: center;
}

.toggle{
    background-color: antiquewhite;
    border-radius: 10px;
    margin: 8px;
    cursor: pointer;
    padding: 5px;
    transition: all 0.2s;
}

.toggle:hover{
    background-color: #e68028;
}
.selected {
    background-color: #e68028;
    color: white;
}
</style>