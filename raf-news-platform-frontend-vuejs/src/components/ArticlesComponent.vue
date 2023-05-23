<template>
  <div class="container">
    <div class="row justify-content-center">
      <div v-for="article in articlesStore.getArticles" :key="article.id">
          <ArticleComponent :article="article" />
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'joi-browser'
import { useArticlesStore } from '../stores/articles'
import { useCategoriesStore } from '../stores/categories'
import ArticleComponent from './ArticleComponent.vue'
import { isNil, isEmpty } from 'ramda'
export default {
  name: 'ArticlesComponent',
  components: { ArticleComponent },
  setup() {
    const articlesStore = useArticlesStore()
    const categoriesStore = useCategoriesStore()
    const searchData = {
      page: 1,
      page_size: 3,
      category_name: 'gaming',
    }

    return {
      articlesStore,
      categoriesStore,
      searchData
    }
  },
  mounted() {
    console.log('articles instantiated')

    if (!isNil(this.categoriesStore.getCategories) && !isEmpty(this.categoriesStore.getCategories)) {
      this.categoriesStore.fetchAllCategories()
    }
    const receivedCategoryName = this.$route.query.category_name
    console.log(receivedCategoryName)
    if (!isNil(receivedCategoryName)) {
      this.searchData.category_name = receivedCategoryName
    }
    console.log('fetching articles')
    this.articlesStore.fetchAllArticlesFiltered(this.searchData)
  },
  methods: {
    goToAddArticlePage() {
      // Implement your goToAddArticlePage method here
    }
  },
}
</script>

<style>
.pushed-in {
    cursor: pointer;
    transition: transform 0.3s ease-in-out;
    transform: scale(0.95);
  }
  .regular-scale {
    cursor: pointer;
    transition: transform 0.3s ease-in-out;
    transform: scale(1);
  }
  .pushed-out {
    cursor: pointer;
    transition: transform 0.3s ease-in-out;
    transform: scale(1.05);
  }
</style>