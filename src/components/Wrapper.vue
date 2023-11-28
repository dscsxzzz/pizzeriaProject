<template>
    <div class="wrapper">
        <slot></slot>
    </div>
</template>
<script>
export default {
    
}
</script>
<style scoped>
    .wrapper {
    top: 0;
    left: 0;
    width: 100vw;
    height: 100cqh;
    height: 100svh;
    position: fixed;
    z-index: 2000000;
    background-color: rgb(0, 0, 0, 0.5);
}
</style>