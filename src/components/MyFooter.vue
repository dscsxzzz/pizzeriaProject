<template>
    <footer>
        <div>
            <h1>Developers:</h1>
            <ul>
                <li>Andrii Krechunaik, Fullstack</li>
            </ul>
        </div>
        <div>
            <h1>Contacts</h1>
            <ul>
                <li>Phone</li>
                <li>E-Mail</li>
            </ul>
        </div>
    </footer>
</template>
<script>
export default {
    
}
</script>
<style scoped>
    footer{
        margin-top: auto;
        display: flex;
        flex-direction: row;
        justify-content: space-around;
        width: 100%;
        height: 150px;
        padding: 30px;
        background-color: rgb(245, 243, 242);
    }

    div{
        display: flex;
        flex-direction: column;
    }

    h1{
        color: #e68028;
    }
    ul{
        list-style: none;
    }
</style>