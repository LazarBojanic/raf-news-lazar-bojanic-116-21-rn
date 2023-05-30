<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col text-center">
        <h1 class="display-4">{{ articlesStore.getArticle.title }}</h1>
        <p class="lead">{{ articlesStore.getArticle.body }}</p>

        <div v-for="articleWithTag in articlesStore.getArticle.tag_list" :key="articleWithTag.id">
          <router-link
            :to="{ name: 'articles', query: { tag_name: articleWithTag.tag.tag_name } }"
            >{{ articleWithTag.tag.tag_name }}</router-link
          >
        </div>

        <div class="my-4">
          <CommentsComponent v-if="articlesStore.getArticle.id" :articleId="articlesStore.getArticle.id" />

        </div>
        
      </div>
    </div>
  </div>
</template>

<script>
import { useArticlesStore } from '../stores/articles'
import CommentsComponent from './CommentsComponent.vue'
import { isNil, isEmpty } from 'ramda'
import { useCommentsStore } from '../stores/comments'

export default {
  name: 'FullArticleComponent',
  components: {
    CommentsComponent
  },
  setup() {
    const articlesStore = useArticlesStore()
    const commentsStore = useCommentsStore()

    return {
      articlesStore,
      commentsStore
    }
  },
  mounted() {
    this.checkArticleIdAndFetchArticle()
  },
  data() {
    return {
      searchData: {
        page: 1,
        page_size: 10
      },
      author: '',
      body: ''
    }
  },
  props: {},
  computed: {},
  methods: {
    async checkArticleIdAndFetchArticle() {
      const urlParams = new URLSearchParams(window.location.search)
      const articleIdParam = urlParams.get('articleId')
      if (!isNil(articleIdParam) || !isEmpty(articleIdParam)) {
        await this.articlesStore.incrementArticleNumberOfViewsById(articleIdParam)
        await this.articlesStore.fetchArticle(articleIdParam)
        //await this.commentsStore.fetchCommentsByArticleIdFiltered(articleIdParam, this.searchData)
        this.$forceUpdate()
      }
    }
  }
}
</script>

<style></style>
