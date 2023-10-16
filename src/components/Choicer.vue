<template>
    <p @click="$emit('handlePage', page)">
        {{ page }}
    </p>
</template>
<script>
export default {
    props: {
        page: {
            type: Number,
            required:true
        }
    }
}
</script>
<style>
    
</style>