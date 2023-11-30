<template>
    <div class="banner">
        <h1>Hot Deals</h1>
            <transition  name="slider" appear>
                <img v-if="choice === 1" src="/imgs/6198806.jpg" alt="" style="width:90%;position:absolute">
                <img v-else-if="choice === 2" src="/imgs/6067838.jpg" alt="" style="width:90%;position:absolute">
                <img v-else-if="choice === 3" src="/imgs/6198806.jpg" alt="" style="width:90%;position:absolute">
            </transition>
        <div class="choice">
            <p @click="choice = 1">
                <span v-if="choice === 1" class="material-symbols-outlined">
                radio_button_checked
                </span>
                <span v-else class="material-symbols-outlined">
                radio_button_unchecked
                </span>
            </p>
            <p @click="choice = 2">
                <span v-if="choice === 2" class="material-symbols-outlined">
                radio_button_checked
                </span>
                <span v-else class="material-symbols-outlined">
                radio_button_unchecked
                </span>
            </p>
            <p @click="choice = 3">
                <span v-if="choice === 3" class="material-symbols-outlined">
                radio_button_checked
                </span>
                <span v-else class="material-symbols-outlined">
                radio_button_unchecked
                </span>
            </p>
        </div>
    </div>
</template>
<script>



export default {
    data() {
        return{
            choice: 1
        }
    },mounted(){
            setInterval(() => {
                if (this.choice === 1) {
                    return this.choice = 2
                } else if (this.choice === 2) {
                    return this.choice = 3
                } else {
                    return this.choice = 1
                }
            }, 5000)
    }
}
</script>
<style scoped>



.material-symbols-outlined {
    color: #e68028;
  font-variation-settings:
  'FILL' 1,
  'wght' 600,
  'GRAD' 0,
  'opsz' 48
}
    .banner{
width: 100%;
min-height: max-content;
padding: 10px;
display: flex;
flex-direction: column;
justify-content: center;
align-items: center;
}

.banner h1 {
    margin-bottom: 28%;
}

.imgs{
    min-width: max-content;

}

.slider-enter-active,
.slider-leave-active {
    transition: all 1s ease-in;
    position: absolute;
}

.slider-enter-from{
transform: translateX(2000px);
  opacity: 0

}
.slider-leave-to {
  transform: translateX(-2000px);
  opacity: 0

}

.choice{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    width: 100px;
}

p{
    cursor: pointer;
    
}


@media only screen and (max-width: 1200px) {
  .slider-enter-from{
transform: translateX(1500px);
  opacity: 0

}
.slider-leave-to {
  transform: translateX(-1500px);
  opacity: 0

}
}
@media only screen and (max-width: 1200px) and (max-height: 601px)  {
  .slider-enter-from{
transform: translateX(1500px);
  opacity: 0

}
.slider-leave-to {
  transform: translateX(-1500px);
  opacity: 0

}
}

@media only screen and (max-width: 900px) and (orientation: portrait)  {
    .slider-enter-from{
transform: translateX(1000px);
  opacity: 0

}
.slider-leave-to {
  transform: translateX(-1000px);
  opacity: 0

}
}

@media only screen and (max-width: 550px) and (orientation: portrait)  {
    .slider-enter-from{
transform: translateX(700px);
  opacity: 0

}
.slider-leave-to {
  transform: translateX(-700px);
  opacity: 0

}
  
}

@media only screen and (max-width: 281px) and (orientation: portrait) {
    .slider-enter-from{
transform: translateX(400px);
  opacity: 0

}
.slider-leave-to {
  transform: translateX(-400px);
  opacity: 0

}
  
}
@media only screen and (max-width: 920px) and (orientation: landscape) {
 .slider-enter-from{
transform: translateX(1000px);
  opacity: 0

}
.slider-leave-to {
  transform: translateX(-1000px);
  opacity: 0

}
}
</style>