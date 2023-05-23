<template>
  <div class="container">
    <div class="row justify-content-center">
      <h1>{{ articlesStore.getArticle.title }}</h1>
      <p>{{ articlesStore.getArticle.body }}</p>
      <br />
      <CommentsComponent :articleId="articlesStore.getArticle.id" />
    </div>
  </div>
</template>

<script>
import { useArticlesStore } from '../stores/articles';
import CommentsComponent from './CommentsComponent.vue'
import {isNil, isEmpty} from 'ramda'
export default {
  name: 'FullArticleComponent',
  components: {
    CommentsComponent
  },
  setup(){
    const articlesStore = useArticlesStore();
    return{
      articlesStore
    }
  },
  mounted() {
    
  },
  data() {
    return {}
  },
  props: {

  },
  computed: {},
  methods: {
    async checkArticleIdAndFetchArticle(){
      const articleIdParam = this.$route.params.articleId;
      if(!isNil(articleIdParam) && !isEmpty(articleIdParam)){
        console.log('fetching article details')
        await this.articlesStore.fetchArticle(articleIdParam);
      }
    }
  }
}
</script>

<style></style>
