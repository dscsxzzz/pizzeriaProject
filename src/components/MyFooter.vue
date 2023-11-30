<template>
    <footer>
        <div>
            <h1>Developers:</h1>
            <ul>
                <li>
                    <a href="https://github.com/dscsxzzz" target="_blank"> Andrii Krechuniak</a>
                </li>
                <li>
                    <a href="https://github.com/Ostapula">
                        Ostap Zabanzhalo
                    </a>
                </li>
            </ul>
        </div>
        <div >
            <h1>Contacts:</h1>
            <div class="contacts">
                    <ul>
                        <li>E-mail: <a href="mailto:krechuniak.a@gmail.com">krechuniak.a@gmail.com</a></li>
                        <li>E-mail: <a href="mailto:ostapnote2@gmail.com">ostapnote2@gmail.com</a></li>
                    </ul>
            </div>
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
    .contacts{
        flex-direction: row;
        justify-content: space-between;
    }
    .contact{
        margin-right: 20px;
    }

    h1{
        color: #e68028;
    }
    ul{
        list-style: none;
    }

    a {
    text-decoration: none;
    color: black;
    position: relative;
    transition: color 0.2s;
    }

    a::after {
    content: "";
    position: absolute;
    bottom: 0;
    left: 0;
    background-color: #e68028;
    width: 0%;
    height: 1px;
    transition: width 0.5s;
    z-index: 0;
    overflow: hidden;
    }

    a:hover::after {
    width: 100%;
    }
</style>