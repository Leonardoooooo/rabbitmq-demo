import { defineConfig } from 'vitepress'

// https://vitepress.dev/reference/site-config
export default defineConfig({
  title: "途牛AI开放平台",
  
  themeConfig: {
    // https://vitepress.dev/reference/default-theme-config
    nav: [
      { text: '首页', link: '/index' },
      { text: '开放平台', link: 'https://platform.tuniu.com' }
    ],
    sidebar: [
      {
        text: '快速开始',
        collapsed: false,
        items: [
          { text: '首次调用', link: 'index' },
          { text: '价格', link: '/guide/price' }
        ]
      },
      {
        text: 'API文档',
        collapsed: false,
        items: [
          { text: '小助手API', 
            collapsed: false,
            items: [
              { text: '小助手API', link: '/apidoc/agent/agentAPI' }
            ]
           },
          { text: '酒店API', 
            collapsed: false,
            items: [
              { text: '酒店列表', link: '/apidoc/hotel/hotelList' },
              { text: '酒店套餐列表', link: '/apidoc/hotel/hotelPackageList' },
              { text: '酒店详情', link: '/apidoc/hotel/hotelDetail' }
            ]
          }
        ]
      }
    ],
    logo: '/logo.png',
    footer: {
      message: 'Powered by VitePress',
      copyright: 'Copyright © 2025 途牛旅游网'
    },
    socialLinks: [
      { icon: 'github', link: 'https://github.com/tuniucorp' }
    ]
  },
  base: '/docs/'
})
